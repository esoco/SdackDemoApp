//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// This file is a part of the 'BasicWebApp' project.
// Copyright 2015 Elmar Sonnenschein, esoco GmbH, Flensburg, Germany
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//	  http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
package org.sdack.app.demo.server.entity;

import de.esoco.entity.Entity;

import org.obrel.core.RelationType;
import org.obrel.type.StandardTypes;

import static org.obrel.core.RelationTypes.newType;


/********************************************************************
 * An simplified entity class that contains user data.
 *
 * @author eso
 */
public class Person extends Entity
{
	//~ Static fields/initializers ---------------------------------------------

	private static final long serialVersionUID = 1L;

	//- Attributes -------------------------------------------------------------
	/** The last or main name of the person. */
	public static final RelationType<String> NAME = StandardTypes.NAME;

	/** The optional first name(s) of a natural person. */
	public static final RelationType<String> FIRST_NAME =
		StandardTypes.FIRST_NAME;

	/** The login name of the person. */
	public static final RelationType<String> LOGIN_NAME = newType();

	/** The hashed value for the login password of the person. */
	public static final RelationType<String> PASSWORD_HASH = newType();
}
