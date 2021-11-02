package com.lindroy.networkrequestpractice.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.lindroy.networkrequestpractice.R

/**
 * @author Lin
 * @date 2021/10/20
 * @function 加载中对话框
 */
class LoadingDialog : DialogFragment() {

    private val screenWidth by lazy {
        val point = Point()
        (requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getRealSize(
            point
        )
        point.x
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null)

    override fun onResume() {
        super.onResume()
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val dialogSize = (screenWidth * 0.25).toInt()
            Log.d("TTT","dialogSize=$dialogSize")
            setLayout(dialogSize, dialogSize)
        }
    }

    companion object {
        private val TAG = LoadingDialog::class.java.simpleName
        private fun showDialog(fm: FragmentManager) {
            (fm.findFragmentByTag(TAG) as? LoadingDialog)?.dismiss()
            LoadingDialog().showNow(fm, TAG)
        }

        private fun dismissDialog(fm: FragmentManager) {
            (fm.findFragmentByTag(TAG) as? LoadingDialog)?.dismiss()
        }

        fun show(activity: FragmentActivity) {
            showDialog(activity.supportFragmentManager)
        }

        fun show(fragment: Fragment) {
            showDialog(fragment.childFragmentManager)
        }

        fun dismiss(activity: FragmentActivity) {
            dismissDialog(activity.supportFragmentManager)
        }

        fun dismiss(fragment: Fragment) {
            dismissDialog(fragment.childFragmentManager)
        }
    }

}