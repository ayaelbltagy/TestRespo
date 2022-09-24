package com.example.productsdemoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.productsdemoapp.databinding.FragmentLoginBinding
import com.example.productsdemoapp.helpers.PreferenceHelper
import com.facebook.*
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import java.util.*


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private var callbackManager: CallbackManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(activity)
        binding.loginButton.setPermissions("email")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val helper = PreferenceHelper(requireContext())
        binding.loginButton.setOnClickListener {
            binding.loginButton.setOnClickListener(View.OnClickListener {
                // Login
                callbackManager = CallbackManager.Factory.create()
                LoginManager.getInstance()
                    .logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
                LoginManager.getInstance().registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(loginResult: LoginResult) {
                            if (loginResult.accessToken.token != null) {
                                helper.setEmail(loginResult.accessToken.token)
                                findNavController().navigate(R.id.action_loginFragment_to_listFragment)
                            }
                            Log.d("MainActivity", "Facebook token: " + loginResult.accessToken.token)
                        }

                        override fun onCancel() {
                            Log.d("MainActivity", "Facebook onCancel.")

                        }

                        override fun onError(error: FacebookException) {
                            Log.d("MainActivity", "Facebook onError.")

                        }
                    })
            })
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }


    fun getLoginResult() {
        LoginManager.getInstance().registerCallback(
            CallbackManager.Factory.create(),
            object : FacebookCallback<LoginResult> {

                override fun onSuccess(loginResult: LoginResult) {

                    val request =
                        GraphRequest.newMeRequest(loginResult.accessToken) { `object`, response ->
                            try {
                                //here is the data that you want

                                if (`object`.has("id")) {
                                    Toast.makeText(requireContext(), "true", Toast.LENGTH_LONG)
                                        .show()
                                } else {
                                    Toast.makeText(requireContext(), "else", Toast.LENGTH_LONG)
                                        .show()
                                }

                            } catch (e: Exception) {
                                e.printStackTrace()
                                Toast.makeText(requireContext(), "ex", Toast.LENGTH_LONG).show()

                            }
                        }

                    val parameters = Bundle()
                    parameters.putString("fields", "name,email,id,picture.type(large)")
                    request.parameters = parameters
                    request.executeAsync()

                }

                override fun onCancel() {
                    Toast.makeText(requireContext(), "cancel", Toast.LENGTH_LONG).show()

                }

                override fun onError(error: FacebookException) {
                    Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_LONG).show()

                }
            })


    }


}