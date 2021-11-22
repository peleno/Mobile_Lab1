package com.example.mobile_lab1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_lab1.viewmodel.SignInViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber
import java.lang.IllegalStateException

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {

    private var signInButton: Button? = null

    private var emailTextInputLayout: TextInputLayout? = null
    private var emailTextView: TextInputEditText? = null

    private var passwordTextInputLayout: TextInputLayout? = null
    private var passwordTextView: TextInputEditText? = null

    private var signInToolbar: Toolbar? = null

    private var viewModel: SignInViewModel? = null

    private lateinit var attachedContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        attachedContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar(view)
        initializeViews(view)
        initializeViewModel()
        signInButton?.setOnClickListener {
            clearInputFieldsError()
            try {
                viewModel?.signIn(emailTextView?.text.toString(), passwordTextView?.text.toString())
            } catch (e: IllegalStateException) {
                Timber.w(e.message!!)
            }
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        viewModel?.emailError?.observe(viewLifecycleOwner) { error ->
            emailTextInputLayout?.error = error
        }
        viewModel?.passwordError?.observe(viewLifecycleOwner) { error ->
            passwordTextInputLayout?.error = error
        }
        viewModel?.user?.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                clearInputFieldsText()
                displaySuccess()
                startActivity(Intent(attachedContext, WelcomeActivity::class.java))
            } else {
                passwordTextInputLayout?.error = "Incorrect email or password"
                Toast.makeText(
                    attachedContext, "Incorrect email or password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SignInFragment.
         */
        @JvmStatic
        fun newInstance() =
            SignInFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun clearInputFieldsError() {
        emailTextInputLayout?.error = null
        passwordTextInputLayout?.error = null
    }

    private fun displaySuccess() {
        clearInputFieldsText()
        val builder: AlertDialog.Builder = AlertDialog.Builder(attachedContext)
        builder.setTitle("Success")
        builder.setMessage("You have successfully signed in!")
        builder.show()
    }

    private fun initializeToolbar(view: View) {
        signInToolbar = view.findViewById(R.id.sign_in_toolbar)
        signInToolbar?.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        signInToolbar?.setNavigationOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, MainFragment.newInstance())?.commit()
        }
    }

    private fun initializeViews(view: View) {
        signInButton = view.findViewById(R.id.sign_in_button)

        emailTextInputLayout = view.findViewById(R.id.email_layout)
        emailTextView = view.findViewById(R.id.email_input_edit_text)

        passwordTextInputLayout = view.findViewById(R.id.password_layout)
        passwordTextView = view.findViewById(R.id.password_input_edit_text)
    }

    private fun clearInputFieldsText() {
        emailTextView?.text?.clear()
        passwordTextView?.text?.clear()
    }
}
