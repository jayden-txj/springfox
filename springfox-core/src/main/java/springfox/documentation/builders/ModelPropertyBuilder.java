/*
 *
 *  Copyright 2015 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package springfox.documentation.builders;

import com.fasterxml.classmate.ResolvedType;
import springfox.documentation.schema.ModelProperty;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.AllowableValues;

import static springfox.documentation.builders.BuilderDefaults.*;
import static springfox.documentation.schema.Enums.*;

public class ModelPropertyBuilder {
  private ResolvedType type;
  private String qualifiedType;
  private int position;
  private Boolean required;
  private String description;
  private AllowableValues allowableValues;
  private String name;
  private boolean isHidden;

  public ModelPropertyBuilder name(String name) {
    this.name = defaultIfAbsent(name, this.name);
    return this;
  }

  public ModelPropertyBuilder type(ResolvedType type) {
    this.type = replaceIfMoreSpecific(type, this.type);
    return this;
  }

  public ModelPropertyBuilder qualifiedType(String qualifiedType) {
    this.qualifiedType = defaultIfAbsent(qualifiedType, this.qualifiedType);
    return this;
  }

  public ModelPropertyBuilder position(int position) {
    this.position = position;
    return this;
  }

  public ModelPropertyBuilder required(boolean required) {
    this.required = required;
    return this;
  }

  public ModelPropertyBuilder description(String description) {
    this.description = defaultIfAbsent(description, this.description);
    return this;
  }

  public ModelPropertyBuilder allowableValues(AllowableValues allowableValues) {
    if (allowableValues != null) {
      if (allowableValues instanceof AllowableListValues) {
        this.allowableValues
            = defaultIfAbsent(emptyListValuesToNull((AllowableListValues) allowableValues), this.allowableValues);
      } else {
        this.allowableValues = defaultIfAbsent(allowableValues, this.allowableValues);
      }
    }
    return this;
  }

  public ModelPropertyBuilder isHidden(boolean isHidden) {
    this.isHidden = isHidden;
    return this;
  }

  public ModelProperty build() {
    return new ModelProperty(name, type, qualifiedType, position, required, isHidden, description, allowableValues);
  }
}