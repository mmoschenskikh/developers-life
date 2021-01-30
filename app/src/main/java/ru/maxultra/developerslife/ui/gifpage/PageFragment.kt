package ru.maxultra.developerslife.ui.gifpage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.maxultra.developerslife.R
import ru.maxultra.developerslife.databinding.FragmentPageBinding
import ru.maxultra.developerslife.network.GifSection

class PageFragment : Fragment() {

    private lateinit var viewModel: PageViewModel
    private lateinit var viewModelFactory: SectionPageViewModelFactory
    private lateinit var section: GifSection

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getInt(ARG_SECTION)?.let { section = GifSection.values()[it] }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentPageBinding>(
            inflater, R.layout.fragment_page, container, false
        )
        if (section == GifSection.RANDOM) {
            viewModel = ViewModelProvider(this).get(RandomPageViewModel::class.java)
        } else {
            viewModelFactory = SectionPageViewModelFactory(section)
            viewModel =
                ViewModelProvider(this, viewModelFactory).get(SectionPageViewModel::class.java)
        }
        binding.viewModel = viewModel
        binding.gifImageView.clipToOutline = true
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    companion object {
        fun newInstance(section: Int) = PageFragment().apply {
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION, section)
            arguments = bundle
        }

        private const val ARG_SECTION = "section_int"
    }
}
