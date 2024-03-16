package id.tugasakhir.sayura

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import id.tugasakhir.sayura.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var clicked = false

    private val fromBottomFabAnim: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.from_bottom_fab)
    }
    private val toBottomFabAnim: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.to_bottom_fab)
    }
    private val rotateClockWiseFabAnim: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.rotate_clock_wise)
    }
    private val rotateAntiClockWiseFabAnim: Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.rotate_anti_clock_wise)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
           onAddButtonClicked()
        }
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)

        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked)
        {
            binding.transparentBg.visibility = View.VISIBLE
            binding.fabCamera.visibility = View.VISIBLE
            binding.fabUpload.visibility = View.VISIBLE
        }
        else
        {
            binding.transparentBg.visibility = View.GONE
            binding.fabCamera.visibility = View.INVISIBLE
            binding.fabUpload.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked)
        {
            binding.fab.startAnimation(rotateClockWiseFabAnim)
            binding.fabCamera.startAnimation(fromBottomFabAnim)
            binding.fabUpload.startAnimation(fromBottomFabAnim)
        }
        else
        {
            binding.fab.startAnimation(rotateAntiClockWiseFabAnim)
            binding.fabCamera.startAnimation(toBottomFabAnim)
            binding.fabUpload.startAnimation(toBottomFabAnim)
        }
    }

    private fun setClickable(clicked: Boolean)
    {
        if (!clicked)
        {
            binding.fabCamera.isClickable = true
            binding.fabUpload.isClickable = true
        }
        else
        {
            binding.fabCamera.isClickable = false
            binding.fabUpload.isClickable = false
        }
    }

    override fun onBackPressed() {
        if (clicked)
        {
            setVisibility(clicked)
            setAnimation(clicked)
        }
        else
        {
            super.onBackPressed()
        }
        clicked = !clicked
    }
}