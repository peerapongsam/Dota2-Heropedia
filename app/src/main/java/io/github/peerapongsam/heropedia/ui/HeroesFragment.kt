package io.github.peerapongsam.heropedia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import io.github.peerapongsam.heropedia.R
import io.github.peerapongsam.heropedia.databinding.FragmentHeroesBinding

class HeroesFragment : BaseFragment<FragmentHeroesBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_heroes

    lateinit var viewModel: HeroesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(HeroesViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setUpView(binding: FragmentHeroesBinding) {
        super.setUpView(binding)
        arguments?.getString(ARGUMENT_HERO_TYPE)?.let {
            viewModel.loadHeroes(it)
        }
        binding.viewModel = viewModel
    }

    companion object {
        private const val ARGUMENT_HERO_TYPE = "hero_type"
        fun newInstance(type: String): HeroesFragment {
            return HeroesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGUMENT_HERO_TYPE, type)
                }
            }
        }
    }
}
