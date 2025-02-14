package com.shinjaehun.permissionhandingcompose

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@ExperimentalPermissionsApi
fun PermissionStatus.isPermanentlyDenied(): Boolean {
    return !shouldShowRationale && !isGranted
}