/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.screens.scenariosimulation.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * It describes how to reach a single property of a fact
 */
@Portable
public class FactMapping {

    /**
     * Expression to reach the property. I.e. person.fullName.last
     */
    private List<ExpressionElement> expressionElements = new LinkedList<>();

    /**
     * Identifier of this expression (it contains the type of expression, i.e. given/expected) - it is mapped to the <b>property</b> header
     */
    private ExpressionIdentifier expressionIdentifier;

    /**
     * Identify the fact by name and class name - it is mapped to <b>Instance</b> header
     */
    private FactIdentifier factIdentifier;

    /**
     * String name of the type of the property described by this class
     */
    private String className;

    /**
     * Alias to customize the <b>instance</b> label
     */
    private String factAlias;

    /**
     * Alias to customize the <b>property</b> label
     */
    private String expressionAlias;

    public FactMapping() {
    }

    public FactMapping(FactIdentifier factIdentifier, ExpressionIdentifier expressionIdentifier) {
        this(expressionIdentifier.getName(), factIdentifier, expressionIdentifier);
    }

    public FactMapping(String factAlias, FactIdentifier factIdentifier, ExpressionIdentifier expressionIdentifier) {
        this.factAlias = factAlias;
        this.expressionIdentifier = expressionIdentifier;
        this.className = factIdentifier.getClassName();
        this.factIdentifier = factIdentifier;
    }

    private FactMapping(FactMapping original) {
        original.expressionElements.forEach(expressionElement -> this.addExpressionElement(expressionElement.getStep(), original.className));
        this.expressionIdentifier = original.expressionIdentifier;
        this.factIdentifier = original.factIdentifier;
        this.className = original.className;
        this.factAlias = original.factAlias;
        this.expressionAlias = original.expressionAlias;
    }

    public String getFullExpression() {
        return expressionElements.stream().map(ExpressionElement::getStep).collect(Collectors.joining("."));
    }

    public List<ExpressionElement> getExpressionElements() {
        return expressionElements;
    }

    public void addExpressionElement(String stepName, String className) {
        this.className = className;
        expressionElements.add(new ExpressionElement(stepName));
    }

    public String getClassName() {
        return className;
    }

    public ExpressionIdentifier getExpressionIdentifier() {
        return expressionIdentifier;
    }

    public FactIdentifier getFactIdentifier() {
        return factIdentifier;
    }

    public String getFactAlias() {
        return factAlias;
    }

    public void setFactAlias(String factAlias) {
        this.factAlias = factAlias;
    }

    public String getExpressionAlias() {
        return expressionAlias;
    }

    public void setExpressionAlias(String expressionAlias) {
        this.expressionAlias = expressionAlias;
    }

    public FactMapping cloneFactMapping() {
        return new FactMapping(this);
    }

    public static String getPlaceHolder(FactMappingType factMappingType) {
        return factMappingType.name();
    }

    public static String getPlaceHolder(FactMappingType factMappingType, int index) {
        return getPlaceHolder(factMappingType) + " " + index;
    }

    public static String getInstancePlaceHolder(int index) {
        return "INSTANCE " + index;
    }

    public static String getPropertyPlaceHolder(int index) {
        return "PROPERTY " + index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FactMapping that = (FactMapping) o;
        return getExpressionElements().equals(that.getExpressionElements()) &&
                Objects.equals(getExpressionIdentifier(), that.getExpressionIdentifier()) &&
                Objects.equals(getFactIdentifier(), that.getFactIdentifier()) &&
                Objects.equals(getClassName(), that.getClassName()) &&
                Objects.equals(getFactAlias(), that.getFactAlias()) &&
                Objects.equals(getExpressionAlias(), that.getExpressionAlias());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExpressionElements(), getExpressionIdentifier(), getFactIdentifier(), getClassName(), getFactAlias(), getExpressionAlias());
    }
}
