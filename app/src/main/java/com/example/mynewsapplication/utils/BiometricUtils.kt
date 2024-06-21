package com.example.mynewsapplication.utils

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.mynewsapplication.R
import com.example.mynewsapplication.ui.screens.MainActivity

interface BiometricAuthenticationCallback {
    fun onAuthenticationSuccess()
}

object BiometricUtils {
    fun checkBiometricSupport(context: Context, callback: BiometricAuthenticationCallback) {
        val biometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                showBiometricPrompt(context, callback)
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Toast.makeText(
                    context,
                    context.getString(R.string.no_biometric_feature_available),
                    Toast.LENGTH_SHORT
                ).show()
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Toast.makeText(
                    context,
                    context.getString(R.string.biometric_feature_are_currently_unavailable),
                    Toast.LENGTH_SHORT
                ).show()
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                Toast.makeText(
                    context,
                    context.getString(R.string.biometric_feature_are_currently_unavailable),
                    Toast.LENGTH_SHORT
                ).show()
                showEnrollFingerprintPrompt(context)
            }

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                // Handle this case
            }

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                // Handle this case
            }

            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                // Handle this case
            }
        }
    }

    private fun showBiometricPrompt(context: Context, callback: BiometricAuthenticationCallback) {
        val executor = ContextCompat.getMainExecutor(context)
        val biometricPrompt = BiometricPrompt(context as MainActivity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        context,
                        context.getString(R.string.authentication_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(
                        context,
                        context.getString(R.string.authentication_succeeded),
                        Toast.LENGTH_SHORT
                    ).show()
                    callback.onAuthenticationSuccess() // Invoke callback on success
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        context,
                        context.getString(R.string.authentication_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(context.getString(R.string.biometric_login_for_my_app))
            .setSubtitle(context.getString(R.string.login_using_biometric))
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun showEnrollFingerprintPrompt(context: Context) {
        val enrollIntent =
            Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                putExtra(
                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
                )
            }
        context.startActivity(enrollIntent)
        Toast.makeText(
            context,
            context.getString(R.string.please_enroll_at_least_one_fingerprint),
            Toast.LENGTH_SHORT
        ).show()
    }
}
