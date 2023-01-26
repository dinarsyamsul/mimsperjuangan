package dev.iconpln.mims.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import dev.iconpln.mims.databinding.FragmentProfileBinding
import dev.iconpln.mims.ui.login.LoginActivity
import dev.iconpln.mims.utils.SessionManager
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var muncul = false
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var session: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        session = SessionManager(requireContext())

        session.user_name.asLiveData().observe(viewLifecycleOwner) {
            binding.tvNamaUser.text = it
        }

        session.nama_cabang.asLiveData().observe(viewLifecycleOwner) {
            binding.tvNamaCabang.text = it
        }

        binding.btnLogoutProfile.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        val dialogTitle = "Yakin?"
        val dialogMessage = "Apakah anda yakin akan melakukan logout?"

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton("Ya") { _, _ ->
                val onLogout = Intent(requireContext(), LoginActivity::class.java)
                onLogout.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP)
                onLogout.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK)

                lifecycleScope.launch {
                    session.clearUserToken()
                }
                session.user_token.asLiveData().observe(viewLifecycleOwner) {
                    android.util.Log.d("MainActivity", "cek token : $it")
                }
                onLogout.flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(onLogout)
                activity?.finish()
            }
            setNegativeButton("Tidak") { dialog, _ ->
                dialog.cancel()
            }
        }

        val alertDialog = alertDialogBuilder.create()
        return alertDialog.show()
    }


}