package com.norbersan.dirtycontexts.beans.longstartup

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MockLongStartupBeanB {
    @Autowired
    lateinit var beanContainer: LongStartupBeanContainer

    @MockkBean
    lateinit var beanB: LongStartupBeanB

    @Test
    fun mockBeanB(){
        every { beanB.toString() } answers { "mocked LongStartupBeanB"}

        println()
        println("----------------------------")
        println("==> MOCKING LongStartupBeanB")
        println("==> " + beanContainer.longStartupBeanA.toString())
        println("==> " + beanContainer.longStartupBeanB.toString())
        println("==> " + beanContainer.longStartupBeanC.toString())
        println("----------------------------")
    }
}