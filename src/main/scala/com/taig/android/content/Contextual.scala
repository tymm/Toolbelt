package com.taig.android.content

import android.content.Context

trait Contextual
{
	implicit def context: Context
}