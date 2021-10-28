package com.norbersan.dirtycontexts.beans.longstartup

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MockLongStartupBeanC {
    @Autowired
    lateinit var beanContainer: LongStartupBeanContainer

    @MockkBean
    lateinit var beanC: LongStartupBeanC

    @Test
    fun mockBeanC(){
        every { beanC.toString() } answers { "mocked LongStartupBeanC"}

        println()
        println("----------------------------")
        println("==> MOCKING LongStartupBeanC")
        println("==> " + beanContainer.longStartupBeanA.toString())
        println("==> " + beanContainer.longStartupBeanB.toString())
        println("==> " + beanContainer.longStartupBeanC.toString())
        println("----------------------------")
    }
}