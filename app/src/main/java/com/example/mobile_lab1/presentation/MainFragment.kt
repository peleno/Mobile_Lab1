package com.example.mobile_lab1.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobile_lab1.R

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    private var signInButton: Button? = null
    private var signUpButton: Button? = null

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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.toolbar))
        initializeViews(view)
        signInButton?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(
                R.id.welcome_container,
                SignInFragment.newInstance()
            )?.commit()
        }

        signUpButton?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(
                R.id.welcome_container,
                SignUpFragment.newInstance()
            )?.commit()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment MainFragment.
         */
        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
    private fun initializeViews(view: View) {
        signInButton = view.findViewById(R.id.sign_in_button)
        signUpButton = view.findViewById(R.id.sign_up_button)
    }
}
