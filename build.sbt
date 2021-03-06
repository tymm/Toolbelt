javacOptions ++=
    "-source" :: "1.7" ::
    "-target" :: "1.7" ::
    "-Xlint:deprecation" ::
    Nil

libraryDependencies ++=
    compilerPlugin( "org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full ) ::
    "com.android.support" % "recyclerview-v7" % "23.1.1" ::
    "com.android.support" % "support-v4" % "23.1.1" ::
    "com.android.support" % "support-v13" % "23.1.1" ::
    "io.taig.android" %% "parcelable" % "2.4.1" ::
    Nil

name := "Toolbelt"

organization := "io.taig.android"

resolvers += Resolver.sonatypeRepo( "snapshots" )

scalaVersion := "2.11.7"

scalacOptions ++=
    "-deprecation" ::
    "-feature" ::
    Nil

version := "0.4.1"