package com.shinjaehun.runningapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView
import com.shinjaehun.runningapp.R
import com.shinjaehun.runningapp.databinding.FragmentSettingsBinding
import com.shinjaehun.runningapp.databinding.FragmentSetupBinding
import com.shinjaehun.runningapp.other.Constants.KEY_NAME
import com.shinjaehun.runningapp.other.Constants.KEY_WEIGHT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment: Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFieldsFromSharedPref()
        binding.btnApplyChanges.setOnClickListener {
            val success = applyChangesToSharedPref()
            if (success) {
                Snackbar.make(view, "Saved changes", Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(view, "Please fill out all the fields", Snackbar.LENGTH_LONG).show()
            }
        }
    }
    private fun loadFieldsFromSharedPref() {
        val name = sharedPreferences.getString(KEY_NAME, "")
        val weight = sharedPreferences.getFloat(KEY_WEIGHT, 80f)
        binding.etName.setText(name)
        binding.etWeight.setText(weight.toString())

    }
    private fun applyChangesToSharedPref(): Boolean {
        val nameText = binding.etName.text.toString()
        val weightText = binding.etWeight.text.toString()

        if (nameText.isEmpty() || weightText.isEmpty()) {
            return false
        }
        sharedPreferences.edit()
            .putString(KEY_NAME, nameText)
            .putFloat(KEY_WEIGHT, weightText.toFloat())
            .apply()

        val toolbarText = "Let's go $nameText"
        val tvToolbarTitle = requireActivity().findViewById<MaterialTextView>(R.id.tvToolbarTitle)
        tvToolbarTitle.text = toolbarText
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}