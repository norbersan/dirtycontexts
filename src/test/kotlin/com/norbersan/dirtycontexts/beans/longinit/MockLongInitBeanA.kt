package com.norbersan.dirtycontexts.beans.longinit

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MockLongInitBeanA {
    @Autowired
    lateinit var beanContainer: LongInitBeanContainer

    @MockkBean
    lateinit var beanA: LongInitBeanA

    @Test
    fun mockBeanA(){
        every { beanA.toString() } answers { "mocked LongInitBeanA"}

        println()
        println("----------------------------")
        println("==> MOCKING LongInitBeanA")
        println("==> " + beanContainer.longInitBeanA.toString())
        println("==> " + beanContainer.longInitBeanB.toString())
        println("==> " + beanContainer.longInitBeanC.toString())
        println("----------------------------")
    }
}