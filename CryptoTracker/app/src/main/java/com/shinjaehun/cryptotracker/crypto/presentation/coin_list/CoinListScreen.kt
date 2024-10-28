package com.shinjaehun.cryptotracker.crypto.presentation.coin_list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.shinjaehun.cryptotracker.R
import com.shinjaehun.cryptotracker.core.presentation.util.toString
import com.shinjaehun.cryptotracker.crypto.presentation.coin_list.components.CoinListItem
import com.shinjaehun.cryptotracker.crypto.presentation.coin_list.components.previewCoin
import com.shinjaehun.cryptotracker.ui.theme.CryptoTrackerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext

@Composable
fun CoinListScreen(
    state: CoinListState,
//    events: Flow<CoinListEvent>, // generic으로 넘김!
    onAction: (CoinListAction) -> Unit,
    modifier: Modifier = Modifier
) {

//    // 요기서 정확히 뭐 하는지 모르겠음...
//    // 걍 error message toast하면 될 거 같은디...
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//    LaunchedEffect(lifecycleOwner.lifecycle) {
//        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
//            withContext(Dispatchers.Main.immediate){
//                events.collect { event ->
//                    when(event){
//                        is CoinListEvent.Error -> {
//                            Toast.makeText(context, event.error.toString(context),Toast.LENGTH_LONG).show()
//                        }
//                    }
//                }
//            }
//        }
//    }

    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
           items(state.coins) { coinUi ->
               CoinListItem(
                   coinUi = coinUi,
                   onClick = { onAction(CoinListAction.OnCoinClick(coinUi)) },
                   modifier = Modifier.fillParentMaxWidth()
               )
               HorizontalDivider()
           }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListScreenPreview() {
    CryptoTrackerTheme {
        CoinListScreen(
            state = CoinListState(
                coins = (1..100).map {
                    previewCoin.copy(id = it.toString())
                }
            ),
//            events = emptyFlow(),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            onAction = {}
        )
    }
}