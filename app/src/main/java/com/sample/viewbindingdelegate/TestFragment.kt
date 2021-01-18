package com.sample.viewbindingdelegate

import android.helper.viewBinding.viewBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sample.viewbindingdelegate.databinding.FragmentTestBinding

class TestFragment : Fragment(R.layout.fragment_test) {

    private val binding: FragmentTestBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.defaultFragmentText.text = getString(R.string.fragment_text)
    }
}