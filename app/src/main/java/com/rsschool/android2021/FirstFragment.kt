package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.Exception

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var generated:  Generated?=null
    private var minEdit: EditText?=null
    private var maxEdit: EditText?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        generated=context as Generated
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"
        minEdit=view.findViewById(R.id.min_value)
        maxEdit=view.findViewById(R.id.max_value)
        generateButton?.setOnClickListener {
            if (minEdit?.text?.isEmpty()==false &&  maxEdit?.text?.isEmpty()==false) {
                try {
                    val min = minEdit?.text.toString().toInt()
                    val max = maxEdit?.text.toString().toInt()
                    if (max>min)
                        generated?.toGenerate(min,max)
                    else
                        Toast.makeText(context, "Max must be greater than Min", Toast.LENGTH_SHORT).show()
                }
                catch (e:Exception) {
                    Toast.makeText(context, "Error "+e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(context,"You need to pass values to Min and Max",Toast.LENGTH_SHORT).show()
            }


        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
    interface Generated
    {
        fun toGenerate(min:Int, max:Int)
    }
}