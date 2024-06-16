
package com.alican.predecessorcompanion.utils

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import kotlin.math.roundToInt


fun Context.loadJSONFromAssets(fileName: String): String {
    return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}

fun formatPhoneNumber(phoneNumber: String): String {
    // Telefon numarasındaki tüm rakam ve + karakterlerini temizle
    val cleanedNumber = phoneNumber.replace(Regex("[^0-9]"), "")

    // Temizlenmiş numarayı formatla
    val formattedNumber = StringBuilder()

    // Numaranın geri kalanını parantez içine al
    if (cleanedNumber.length >= 4) {
        formattedNumber.append("(").append(cleanedNumber.substring(1, 4)).append(")")
    }

    if (cleanedNumber.length >= 7) {
        formattedNumber.append(cleanedNumber.substring(4, 7))
    }

    if (cleanedNumber.length >= 9) {
        formattedNumber.append("-").append(cleanedNumber.substring(7, 9))
    }

    if (cleanedNumber.length >= 11) {
        formattedNumber.append("-").append(cleanedNumber.substring(9, 11))
    }

    return formattedNumber.toString()
}

fun Context.openChrome(url: String) {
    val urlIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    )
    this.startActivity(urlIntent)
}


@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}

fun restartApp(context: Context) {
    val packageManager: PackageManager = context.packageManager
    val intent: Intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
    val componentName: ComponentName = intent.component!!
    val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
    context.startActivity(restartIntent)
    Runtime.getRuntime().exit(0)
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun hasNotificationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.POST_NOTIFICATIONS
    ) == PackageManager.PERMISSION_GRANTED
}


fun statusBarHeight(resources: Resources): Float {
    val deviceDensity = resources.displayMetrics.density
    var statusHeight: Float
    val idStatusBarHeight: Int =
        resources.getIdentifier("status_bar_height", "dimen", "android")
    statusHeight = if (idStatusBarHeight > 0)
        resources.getDimensionPixelSize(idStatusBarHeight) / deviceDensity
    else 0f
    statusHeight = statusHeight.roundToInt().toFloat()
    return statusHeight
}

fun hideStatusBarPadding(
    resources: Resources,
    window: Window
) {
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.decorView.setPadding(0, 0, 0, resources.getDimensionPixelSize(resourceId))
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.decorView.windowInsetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
        )
    }
}

fun Modifier.noRippleClick(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    ) {
        onClick()
    }
}


@Composable
fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

