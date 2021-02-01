package main;

import com.sun.source.util.Trees;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.Set;

@SupportedAnnotationTypes(
        "main.EnabledSubsystem")
public class AnnotationProcessor extends AbstractProcessor {
    Trees trees;
    @Override
    public void init(ProcessingEnvironment pe) {
        super.init(pe);
        this.trees = Trees.instance(pe);
    }
    @Override
    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedClasses = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element mClass : annotatedClasses) {
                try {
                    String className = mClass.getSimpleName().toString();
                    String packageName = null;
                    int lastDot = className.lastIndexOf('.');
                    if (lastDot > 0) {
                        packageName = className.substring(0, lastDot);
                    }

                    String simpleClassName = className.substring(lastDot + 1);
                    String builderClassName = className + "Base";
                    String builderSimpleClassName = builderClassName
                            .substring(lastDot + 1);
                    JavaFileObject builderFile = processingEnv.getFiler()
                            .createSourceFile(builderClassName);

                    PrintWriter out = new PrintWriter(builderFile.openWriter());

                    if (packageName != null) {
                        out.print("package ");
                        out.print(packageName);
                        out.println(";");
                        out.println();
                    }

                    out.print("public class ");
                    out.print(builderSimpleClassName);
                    out.println(" {");
                    out.println();
                } catch (Throwable ignored) {
                }
            }
        }
        return false;
    }
}
