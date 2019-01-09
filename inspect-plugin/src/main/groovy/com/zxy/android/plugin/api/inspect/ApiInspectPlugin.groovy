package com.zxy.android.plugin.api.inspect

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by zhengxiaoyong on 2018/12/22.
 */
class ApiInspectPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.extensions.create("apiInspect", ApiInspectExtension.class, project)

        if (project.plugins.hasPlugin("com.android.library")) {
            return
        }

        def android = null
        if (project.plugins.hasPlugin("com.android.application")) {
            android = project.extensions.getByType(AppExtension.class)
        }

        if (android == null)
            return
        android.registerTransform(new ApiInspectTransform(project))
    }
}
