/**
 * Licensed to Cloudera, Inc. under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Cloudera, Inc. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloudera.labs.envelope.output;

import com.cloudera.labs.envelope.load.LoadableFactory;
import com.typesafe.config.Config;

public class OutputFactory extends LoadableFactory<Output> {

  public static final String TYPE_CONFIG_NAME = "type";

  public static Output create(Config config) {
    if (!config.hasPath(TYPE_CONFIG_NAME)) {
      throw new RuntimeException("Output type not specified");
    }

    String outputType = config.getString(TYPE_CONFIG_NAME);
    Output output = null;
    try {
      output = loadImplementation(Output.class, outputType);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

    output.configure(config);

    return output;
  }

}
