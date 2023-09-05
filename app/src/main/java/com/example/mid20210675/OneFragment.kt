package com.example.mid20210675

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.mid20210675.databinding.FragmentOneBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //month는 0부터 시작, 화면에 출력하는 창은 +1을 해줘야 함
        val binding = FragmentOneBinding.inflate(inflater, container, false)

        binding.dateBtn.setOnClickListener{


            var tyear=""
            var tmonth=""
            var tday=""

            DatePickerDialog(requireContext(), object: DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    binding.dateBtn.setText("${year}년 ${month+1}월 ${dayOfMonth}일")
                    tyear = year.toString()
                    tmonth = (month+1).toString()
                    tday = dayOfMonth.toString()
                }
            },2023,4,1).show()
        }

        binding.timeBtn.setOnClickListener{
            TimePickerDialog(requireContext(),object: TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    binding.timeBtn.setText("${hourOfDay}시 ${minute}분")
                }
            },15,0,true).show()
        }

        val rooms = arrayOf("Room1","Room2","Room3")
        var myroom =""
        val alertHandler = object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE->{
                        binding.radioBtn.setText("$myroom")
                        Toast.makeText(context,"$myroom 이 최종 선택되었습니다.",Toast.LENGTH_SHORT).show()
                    }
                    DialogInterface.BUTTON_NEGATIVE->{
                        Toast.makeText(context,"예약룸 선택이 취소되었습니다.",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.radioBtn.setOnClickListener{
            AlertDialog.Builder(context).run{
                setTitle("예약룸 선택하기")
                setSingleChoiceItems(rooms,0,object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d("mobile","${rooms[which]}룸이 선택됨")
                        myroom = rooms[which]
                    }
                })
                setPositiveButton("OK",alertHandler)
                setNegativeButton("Cancel",alertHandler)
                show()
            }
        }

        binding.subBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                binding.textbox.isVisible=true
                binding.textbox.setText("예약자는 예약날짜는 ")
            }
        })


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}