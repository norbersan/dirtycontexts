package com.norbersan.dirtycontexts.beans.basic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

abstract class AbstractBean(){
    val id = UUID.randomUUID()

    override fun toString() = "==> bean ${javaClass.simpleName} ($id)"

    init {
        println("==> ${javaClass.simpleName} ($id) init in thread ${Thread.currentThread()}")
    }

    @PostConstruct
    fun setup(){
        println("==> ${javaClass.simpleName} ($id) @Postconstruct in thread ${Thread.currentThread()}")
    }

    @PreDestroy
    fun teardown(){
        println("==> ${javaClass.simpleName} ($id) @Predestroy in thread ${Thread.currentThread()}")
    }
}

@Component
class BeanA() : AbstractBean()

@Component
class BeanB() : AbstractBean()

@Component
class BeanC() : AbstractBean()

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class BeanContainer() : AbstractBean() {
    @Autowired
    lateinit var beanA: BeanA

    @Autowired
    lateinit var beanB: BeanB

    @Autowired
    lateinit var beanC: BeanC
}