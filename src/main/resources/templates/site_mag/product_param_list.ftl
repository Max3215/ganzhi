<#if product?? && product.totalSelects??>
    <#list 1..product.totalSelects as index>
        <#if 1==index>
            <dl>
                <dt>${product.selectOneName!''}</dt>
                <dd><input name="selectOneValue" type="text" value="<#if goods??>${goods.selectOneValue!''}</#if>" class="input txt100" datatype="*1-100" sucmsg=""></dd>
            </dl>
        <#elseif 2==index>
            <dl>
                <dt>${product.selectTwoName!''}</dt>
                <dd><input name="selectTwoValue" type="text" value="<#if goods??>${goods.selectTwoValue!''}</#if>" class="input txt100" datatype="*1-100" sucmsg=""></dd>
            </dl>
        <#elseif 3==index>
            <dl>
                <dt>${product.selectThreeName!''}</dt>
                <dd><input name="selectThreeValue" type="text" value="<#if goods??>${goods.selectThreeValue!''}</#if>" class="input txt100" datatype="*1-100" sucmsg=""></dd>
            </dl>
        </#if>
    </#list>
</#if>