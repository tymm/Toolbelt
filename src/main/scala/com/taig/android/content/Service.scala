package com.taig.android.content

trait Service extends android.app.Service with Context
{
	override protected[content] implicit val context = this
}