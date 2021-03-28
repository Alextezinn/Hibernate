package ru.sfedu.hiber.lab1.api;

import java.util.List;

public interface IMetaDataProvider {
    List getListSchemas();

    List getListTables();

    List getListTablesType();

    List getListRoleTablesGrants();
}
