package com.example.artventureindonesia.ui.costumeDialog


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.artventureindonesia.R
import com.example.artventureindonesia.ui.detailtask.DetailTaskActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.artventureindonesia.ui.task.TaskActivity
import com.example.artventureindonesia.ui.uploadtask.UploadTaskActivity


class AlertDialogCostume: DialogFragment() {


    var result = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val rootView: View = layoutInflater.inflate(R.layout.costume_alert, null)
        val tvTitle: TextView = rootView.findViewById<TextView>(R.id.tv_status)
        val ivResult: ImageView = rootView.findViewById<ImageView>(R.id.img_status)
        val btnAction: MaterialButton = rootView.findViewById<MaterialButton>(R.id.btn_action)
        val AlertDialog = MaterialAlertDialogBuilder(requireContext())
            .setView(rootView)
            .create()

        if (result){
            tvTitle.text = resources.getString(R.string.kamu_berhasil)
            ivResult.setImageResource(R.drawable.active)
            btnAction.apply {
                setText(R.string.ok)
                setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green_alert))
            }
        } else {
            tvTitle.text = resources.getString(R.string.kamu_gagal)
            ivResult.setImageResource(R.drawable.delice)
            btnAction.apply {
                setText(R.string.ok)
                setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            }
        }
        btnAction.setOnClickListener{
            if (result){
                val intent = Intent(requireContext(), TaskActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                activity?.finish()
            } else {

                AlertDialog.cancel()
            }
        }
        AlertDialog.setCanceledOnTouchOutside(false)
        return AlertDialog
    }



    companion object {
        fun newInstance(message: String): AlertDialogCostume {
            val fragment = AlertDialogCostume()
            val args = Bundle()
            args.putString("message", message)
            fragment.arguments = args
            return fragment
        }
    }


}