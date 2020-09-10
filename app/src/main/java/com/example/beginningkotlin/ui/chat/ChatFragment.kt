package com.example.beginningkotlin.ui.chat

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.beginningkotlin.R
import com.example.beginningkotlin.data.response_model.FriendlyMessage
import com.example.beginningkotlin.databinding.FragmentChatBinding
import com.example.beginningkotlin.ui.base.BaseFragment
import com.example.beginningkotlin.ui.chat.adapter.ChatAdapter
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_chat.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class ChatFragment : BaseFragment<ChatViewModel>() {
    var navController: NavController? = null
    private val chatViewModel: ChatViewModel by viewModel()
    var binding: FragmentChatBinding? = null
    final val ANONYMOUS = "anonymous"
    private final val RC_SIGN_IN : Int = 1
    private var mMessageAdapter: ChatAdapter? = null
    private var mUsername: String? = null
    val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_chat,
            container, false
        )
        return binding!!.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding?.viewModel = viewModel
        onTextChangeListener()

        viewModel.messageAdded.observe(viewLifecycleOwner, Observer { messageAdded ->
            mMessageAdapter!!.add(messageAdded)
        })
        viewModel.userAuthStateChanged.observe(viewLifecycleOwner, Observer { user ->
            if(user != null) {
                mUsername = user.displayName
                viewModel.attachChildEventListener()
            } else {
                mUsername = ANONYMOUS
                mMessageAdapter?.clear()
                viewModel.deattachChildEventListener()
                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(
                            Arrays.asList(
                                AuthUI.IdpConfig.EmailBuilder().build(),
                                AuthUI.IdpConfig.GoogleBuilder().build()
                            )
                        )
                        .build(),
                    RC_SIGN_IN
                )
            }
        })
        viewModel.attachChildEventListener()
        //user
        mUsername = ANONYMOUS
        //adapter
        val friendlyMessages: List<FriendlyMessage?> = ArrayList()
        mMessageAdapter = ChatAdapter(context, R.layout.message_item, friendlyMessages)
        fragment_chat_list_view!!.adapter = mMessageAdapter
        // Enable Send button when there's text to send
        onTextChangeListener()
        onSignoutClickListener()
        messageEditText!!.filters = arrayOf<InputFilter>(
            InputFilter.LengthFilter(
                100
            )
        )
        // Send button sends a message and clears the EditText
        onSendButtonClickListener()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN)
            if (resultCode == AppCompatActivity.RESULT_OK) {
                mUsername = FirebaseAuth.getInstance().currentUser!!.displayName
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.sign_out_menu) {
            AuthUI.getInstance().signOut(requireContext())
            return true
        } else
            return super.onOptionsItemSelected(item)
    }
    private fun onSendButtonClickListener() {
        sendButton!!.setOnClickListener {
            viewModel.sendMessage(mUsername!!)
            showToast("hi")
            // Clear input box
            messageEditText!!.setText("")
        }
    }
    private fun onTextChangeListener() {
        binding!!.messageEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,  i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(
                charSequence: CharSequence,  i: Int, i1: Int, i2: Int
            ) {
                binding!!.sendButton.isEnabled = charSequence.toString().trim().length > 0
            }
            override fun afterTextChanged(editable: Editable) {}
        })
    }
    private fun onSignoutClickListener() {
        fragment_chat_signout.setOnClickListener {
            AuthUI.getInstance().signOut(requireContext())
            navController!!.navigate(R.id.action_chatFragment_to_popularMoviesFragment)

        }
    }
    override fun getViewModelType(): ChatViewModel {
        return chatViewModel
    }
    override fun onPause() {
        super.onPause()
        viewModel.deattachAuthListener()
        viewModel.deattachAuthListener()
        mMessageAdapter?.clear()
    }

    override fun onResume() {
        super.onResume()
        viewModel.attachAuthEventListener()
    }
}
