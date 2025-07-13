package com.quiz.myquizapplication

import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.Navigation
import com.quiz.myquizapplication.databinding.FragmentCategoriesBinding
import com.quiz.myquizapplication.databinding.FragmentEntryBinding


class CategoriesFragment : Fragment() , PopupMenu.OnMenuItemClickListener{

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var popup : PopupMenu


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.exitButton.setOnClickListener{floatingButtonExit(it)}

        popup = PopupMenu(requireContext(),binding.exitButton)
        val inflater= popup.menuInflater
        inflater.inflate(R.menu.my_popup_menu , popup.menu)
        popup.setOnMenuItemClickListener(this)
    }

    fun floatingButtonExit (view: View){

        popup.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.exitButton) {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToEntryFragment()
            Navigation.findNavController(requireView()).navigate(action)
            return true
        }
        return false
    }



}