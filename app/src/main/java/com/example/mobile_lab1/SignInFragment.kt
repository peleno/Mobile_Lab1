package com.example.mobile_lab1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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
        signInButton?.setOnClickListener {
            val isValidationSuccessful = validateInputFields()
            if (isValidationSuccessful) {
                displaySuccess()
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

    private fun validateInputFields(): Boolean {
        clearInputFieldsError()
        val validEmail: Boolean =
            InputTextValidator.validateEmail(emailTextView, emailTextInputLayout)
        val validPassword: Boolean =
            InputTextValidator.validatePassword(passwordTextView, passwordTextInputLayout)
        return validPassword && validEmail
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
