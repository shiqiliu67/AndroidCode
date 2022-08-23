package com.example.magiclinkdemo

import java.util.*

object B2CConfiguration {

    val Policies = arrayOf(
        "B2C_1A_SIGNIN_OIDC", "B2C_1A_SIGNIN_MAGICLINK_OIDC","B2C_1A_SIGNUP_OIDC" ,"B2C_1A_PROFILEEDIT_OIDC","B2C_1A_FORGOTPASSWORD_OIDC"
    )

    /**
     * Name of your B2C tenant hostname.
     */
    const val azureAdB2CHostName ="B2CGPSTG.b2clogin.com"//"B2CGPSTG.b2clogin.com"// "B2CGPDEV.b2clogin.com"

    /**
     * Name of your B2C tenant.
     */
    const val tenantName ="B2CGPSTG.onmicrosoft.com"//"B2CGPDEV.onmicrosoft.com"

    /**
     * Returns an authority for the given policy name.
     *
     * @param policyName name of a B2C policy.
     */
    fun getAuthorityFromPolicyName(policyName: String): String {

        return "https://$azureAdB2CHostName/tfp/$tenantName/$policyName/"
    }

    /**
     * Returns an array of scopes you wish to acquire as part of the returned token result.
     * These scopes must be added in your B2C application page.
     */
    val scopes: List<String>
        get() = Arrays.asList(
            "https://B2CGPSTG.onmicrosoft.com/05f46cda-b344-44b0-b70f-153f1df89854/read"

        )
}
