package ru.maxultra.developerslife.ui.gifpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.maxultra.developerslife.R
import ru.maxultra.developerslife.databinding.FragmentPageBinding

class PageFragment : Fragment() {

    private val viewModel: PageViewModel by lazy {
        ViewModelProvider(this).get(PageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentPageBinding>(
            inflater, R.layout.fragment_page, container, false
        )
        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}
