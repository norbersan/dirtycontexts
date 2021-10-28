package com.norbersan.dirtycontexts.beans.longstartup

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MockLongStartupBeanA {
    @Autowired
    lateinit var beanContainer: LongStartupBeanContainer

    @MockkBean
    lateinit var beanA: LongStartupBeanA

    @Test
    fun mockBeanA(){
        every { beanA.toString() } answers { "mocked LongStartupBeanA"}

        println()
        println("----------------------------")
        println("==> MOCKING LongStartupBeanA")
        println("==> " + beanContainer.longStartupBeanA.toString())
        println("==> " + beanContainer.longStartupBeanB.toString())
        println("==> " + beanContainer.longStartupBeanC.toString())
        println("----------------------------")
    }
}