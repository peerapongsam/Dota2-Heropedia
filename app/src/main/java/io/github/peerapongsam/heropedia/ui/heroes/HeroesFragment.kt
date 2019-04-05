package io.github.peerapongsam.heropedia.ui.heroes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import io.github.peerapongsam.heropedia.BR
import io.github.peerapongsam.heropedia.InjectorUtil
import io.github.peerapongsam.heropedia.R
import io.github.peerapongsam.heropedia.databinding.FragmentHeroesBinding
import io.github.peerapongsam.heropedia.ui.base.BaseFragment
import io.github.peerapongsam.heropedia.ui.detail.HeroDetailActivity
import io.github.peerapongsam.heropedia.util.activityViewModelProvider
import kotlinx.android.synthetic.main.activity_hero_detail.image

class HeroesFragment : BaseFragment<FragmentHeroesBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_heroes

    lateinit var viewModel: HeroesViewModel

    var viewPool: RecyclerView.RecycledViewPool? = null

    override fun createViewModel() {
        val viewModelFactory = InjectorUtil.provideHeroesViewModelFactory()
        viewModel = activityViewModelProvider(viewModelFactory)
    }

    override fun setUpView(binding: FragmentHeroesBinding) {
        super.setUpView(binding)

        val heroesAdapter = HeroesAdapter().apply {
            setOnClickListener { hero, imageView, textView ->
                Log.d(TAG, "setUpView() called with: it = [$hero]")

                val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    requireActivity(),
                    Pair(imageView as View, "image_${hero.id}"),
                    Pair(textView as View, "name_${hero.id}")
                )
                startActivity(
                    Intent(requireContext(), HeroDetailActivity::class.java).apply {
                        putExtras(Bundle().apply {
                            putParcelable(HeroDetailActivity.EXTRA_HERO, hero)
                        })
                    }, optionsCompat.toBundle()
                )
            }
        }

        binding.heroesList.apply {
            adapter = heroesAdapter
            setRecycledViewPool(viewPool)
        }

        viewModel.getHeroes(arguments?.getString(ARGUMENT_HERO_TYPE).orEmpty()).observe(this, Observer {
            binding.setVariable(BR.heroesItems, it)
        })
    }

    companion object {
        private const val TAG = "HeroesFragment"
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
