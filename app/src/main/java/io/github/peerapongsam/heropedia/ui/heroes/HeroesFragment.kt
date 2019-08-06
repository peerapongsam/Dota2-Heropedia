package io.github.peerapongsam.heropedia.ui.heroes

import android.content.Intent
import android.os.Bundle
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
import io.github.peerapongsam.heropedia.util.activityViewModelProvider
import timber.log.Timber
import android.media.AudioManager
import android.media.MediaPlayer
import io.github.peerapongsam.heropedia.ui.detail.HeroDetailActivity

class HeroesFragment : BaseFragment<FragmentHeroesBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_heroes

    lateinit var viewModel: HeroesViewModel

    var mediaPlayer: MediaPlayer = MediaPlayer()

    var viewPool: RecyclerView.RecycledViewPool? = null

    override fun createViewModel() {
        val viewModelFactory = InjectorUtil.provideHeroesViewModelFactory()
        viewModel = activityViewModelProvider(viewModelFactory)
    }

    override fun setUpView(binding: FragmentHeroesBinding) {
        super.setUpView(binding)

        val heroesAdapter = HeroesAdapter().apply {
            setOnClickListener { hero, imageView, textView ->
                Timber.d("setUpView() called with: hero = [$hero], imageView = [$imageView], textView = [$textView]")
                val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    requireActivity(),
                    Pair(imageView as View, "image_${hero.id}"),
                    Pair(textView as View, "name_${hero.id}")
                )
                playResponse(hero.id)
                startActivity(
                    Intent(requireContext(), HeroDetailActivity::class.java).apply {
                        putExtras(Bundle().apply {
                            putString(HeroDetailActivity.EXTRA_HERO, hero.id)
                        })
                    }, optionsCompat.toBundle()
                )
            }
        }

        binding.heroesList.apply {
            adapter = heroesAdapter
            setRecycledViewPool(viewPool)
        }

        viewModel.getHeroes(arguments?.getString(ARGUMENT_HERO_TYPE).orEmpty())
            .observe(this, Observer {
                binding.setVariable(BR.heroesItems, it)
            })
    }

    private fun playResponse(id: String) {
        try {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.reset()
            }
            mediaPlayer = MediaPlayer()
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            val descriptor = requireContext().assets.openFd("$id.mp3")
            mediaPlayer.setDataSource(
                descriptor.fileDescriptor,
                descriptor.startOffset,
                descriptor.length
            )
            descriptor.close()
            mediaPlayer.prepare() // might take long! (for buffering, etc)
            mediaPlayer.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
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
