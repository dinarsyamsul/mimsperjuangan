package dev.iconpln.mims.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import dev.iconpln.mims.databinding.FragmentProfileBinding
import dev.iconpln.mims.utils.SessionManager

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

        session.user_name.asLiveData().observe(viewLifecycleOwner){
            binding.tvNamaUser.text = it
        }

        session.nama_cabang.asLiveData().observe(viewLifecycleOwner){
            binding.tvNamaCabang.text = it
        }
    }

}