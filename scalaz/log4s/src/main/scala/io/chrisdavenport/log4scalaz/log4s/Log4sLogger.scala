package io.chrisdavenport.log4cats.log4s

import io.chrisdavenport.log4cats._
import org.log4s.{Logger => Base}
import scalaz.ioeffect.{IO, Void}

object Log4sLogger {

  def createLocal = fromLog4s(org.log4s.getLogger)
  def createByName(name: String) = fromLog4s(org.log4s.getLogger(name))
  def createByClass(clazz: Class[_]) = fromLog4s(org.log4s.getLogger(clazz))

  def fromLog4s(logger: Base): SelfAwareLogger[IO[Void, ?]] = new SelfAwareLogger[IO[Void, ?]] {
    override def isTraceEnabled: IO[Void, Boolean] =
      IO.sync(logger.isTraceEnabled)
    override def isDebugEnabled: IO[Void, Boolean] = 
      IO.sync(logger.isDebugEnabled)
    override def isInfoEnabled: IO[Void, Boolean] =
      IO.sync(logger.isInfoEnabled)
    override def isWarnEnabled: IO[Void, Boolean] =
      IO.sync(logger.isWarnEnabled)
    override def isErrorEnabled: IO[Void, Boolean] =
      IO.sync(logger.isErrorEnabled)

    override def error(message: => String): IO[Void, Unit] =
      IO.sync(logger.error(message))
    override def error(t: Throwable)(message: => String): IO[Void, Unit] = 
      IO.sync(logger.error(t)(message))

    override def warn(message: => String): IO[Void, Unit] =
      IO.sync(logger.warn(message))
    override def warn(t: Throwable)(message: => String): IO[Void, Unit] =
      IO.sync(logger.warn(t)(message))

    override def info(message: => String): IO[Void, Unit] =
      IO.sync(logger.info(message))
    override def info(t: Throwable)(message: => String): IO[Void, Unit] = 
      IO.sync(logger.info(t)(message))

    override def debug(message: => String): IO[Void, Unit] = 
      IO.sync(logger.debug(message))
    override def debug(t: Throwable)(message: => String): IO[Void, Unit] =
      IO.sync(logger.debug(t)(message))

    override def trace(message: => String): IO[Void, Unit] = 
      IO.sync(logger.trace(message))
    override def trace(t: Throwable)(message: => String): IO[Void, Unit] = 
      IO.sync(logger.trace(t)(message))
  }
}