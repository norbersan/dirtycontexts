package com.norbersan.dirtycontexts.beans

import com.norbersan.dirtycontexts.beans.AbstractBasicBean as AbstractBean
//import com.norbersan.dirtycontexts.beans.AbstractLongInitBean as AbstractBean
//import com.norbersan.dirtycontexts.beans.AbstractLongStartupBean as AbstractBean
//import com.norbersan.dirtycontexts.beans.AbstractAsyncInitBean as AbstractBean
//import com.norbersan.dirtycontexts.beans.AbstractAsyncSetupBean as AbstractBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
class BeanA : AbstractBean()

@Component
class BeanB : AbstractBean()

@Component
class BeanC : AbstractBean()

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