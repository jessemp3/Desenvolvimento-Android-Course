package com.jesse.whatsapp.util

import androidx.core.view.isVisible
import com.jesse.whatsapp.databinding.ToolbarCustomBinding

fun ToolbarCustomBinding.setup(
    title: String,
    onBack: () -> Unit,
) {
    toolbar.title = title

    toolbar.setNavigationOnClickListener {
        onBack()
    }
}