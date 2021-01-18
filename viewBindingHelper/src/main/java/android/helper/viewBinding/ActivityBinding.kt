package android.helper.viewBinding

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : ViewBinding> Activity.viewBinding() =
        ActivityBindingHelper(T::class.java)

class ActivityBindingHelper<T : ViewBinding>(private val bindingClass: Class<T>) : ReadOnlyProperty<Activity, T> {

    private var binding: T? = null

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        binding?.let { return it }

        //Getting inflate method from ViewBinding class
        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)

        //Invoking specific ViewBinding layout passed as a parameter
        val invokeLayout = inflateMethod.invoke(null, thisRef.layoutInflater) as T

        //Setting up the view of the activity
        thisRef.setContentView(invokeLayout.root)

        //Returning the binding object
        return invokeLayout.also { this.binding = it }
    }

}