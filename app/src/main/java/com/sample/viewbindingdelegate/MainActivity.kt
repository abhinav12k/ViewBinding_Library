package com.sample.viewbindingdelegate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.helper.viewBinding.viewBinding
import android.opengl.Visibility
import android.view.View
import com.sample.viewbindingdelegate.databinding.ActivityMainBinding

/**
 * Created by abhinav on 18/1/21.
 */

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.showFragmentBtn.setOnClickListener {
            binding.fragmentContainer.visibility = View.VISIBLE
            val fragment = TestFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(binding.fragmentContainer.id, fragment)
            transaction.commit()

            binding.showFragmentBtn.visibility = View.INVISIBLE
            binding.sampleTextMain.visibility = View.INVISIBLE
        }

    }
}