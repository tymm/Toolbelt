package com.taig.android.content.activity

import android.os.Bundle
import android.view.{MenuItem, View}
import android.widget.FrameLayout
import com.taig.android.content._
import com.taig.android.content.activity.Drawer.Parameter
import com.taig.android.util.Companion
import com.taig.android.{R, content}
import com.taig.{android=>taig}

trait	Drawer
extends	Activity
{
	require( !this.isInstanceOf[Back], "Can't use both: Drawer and Back, pick one!" )

	def drawer: Drawer.Property

	override def onCreate( state: Option[Bundle] )
	{
		super.onCreate( state )

		setRootView( R.layout.drawer )

		this match
		{
			// TODO load icon depending on dark or light theme
			case activity: ActionBar => activity.actionbar.main.setNavigationIcon( R.drawable.icon_light_hamburger )
			case _ => // Nothing to do here ...
		}

		// Prepare navigation drawer
		drawer.wrapper.addView( drawer.widget )

		// Restore drawer state: opened or closed?
		state
			.map( _.getBoolean( Parameter.Drawer, false ) )
			.collect{ case true => drawer.root.openDrawer( drawer.wrapper ) }
	}

	/**
	 * Close navigation drawer when the back key is pressed
	 */
	override def onBackPressed() =
	{
		if( drawer.isOpen() )
		{
			drawer.close()
		}
		else
		{
			super.onBackPressed()
		}
	}

	override def onOptionsItemSelected( item: MenuItem ) = item.getItemId match
	{
		case android.R.id.home =>
		{
			if( drawer.isOpen() )
			{
				drawer.close()
			}
			else
			{
				drawer.open()
			}

			true
		}
		case _ => super.onOptionsItemSelected( item )
	}

	override def onSaveInstanceState( state: Bundle )
	{
		super.onSaveInstanceState( state )

		// Save drawer state: open or closed?
		state.putBoolean( Parameter.Drawer, drawer.isOpen() )
	}
}

object	Drawer
extends	Companion
{
	val Parameter = new
	{
		val Drawer = Tag + ".Drawer"
	}

	trait	Property
	extends	content.Property[Drawer]
	{
		def close() = root.closeDrawer( wrapper )

		def isOpen() = root.isDrawerOpen( wrapper )

		def open() = root.openDrawer( wrapper )

		lazy val root = content.find[taig.widget.Drawer]( R.id.drawer_root )

		lazy val wrapper = content.find[FrameLayout]( R.id.drawer )

		/**
		 * The actual drawer layout
		 */
		def widget: View
	}
}