package android.helper.viewBinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : ViewBinding> Fragment.viewBinding() = FragmentBinding(T::class.java, this);

class FragmentBinding<T : ViewBinding>(
        bindingClass: Class<T>,
        private val fragment: Fragment
) : ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null

    //Getting the binding method from ViewBinding class
    private val bindMethod = bindingClass.getMethod("bind", View::class.java)

    init {
        //Setting an observer on the lifecycle to stop memory leak when the fragment is destroyed
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            binding = null
                        }
                    })
                }
            }
        })
    }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        binding?.let { return it }

        //Getting the state of lifecycle of Fragment
        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. View lifecycle is ${lifecycle.currentState}")
        }

        //Getting invoke method from the binding method
        val invoke = bindMethod.invoke(null, thisRef.requireView()) as T

        //Returning the binding object
        return invoke.also { this.binding = it }
    }

}