package com.sksamuel.kotlintest.specs.freespec

import io.kotlintest.Description
import io.kotlintest.Spec
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec
import java.util.concurrent.atomic.AtomicInteger

class FreeSpecMultipleInvocationTest : FreeSpec() {

  val counter = AtomicInteger(0)

  init {

    "a" {
      counter.incrementAndGet()
    }

    "b".config(invocations = 3) {
      counter.incrementAndGet()
    }

    "c" - {
      counter.incrementAndGet()
      "d".config(invocations = 4) {
        counter.incrementAndGet()
      }
    }
  }

  override fun afterSpec(description: Description, spec: Spec) {
    counter.get().shouldBe(9)
  }
}