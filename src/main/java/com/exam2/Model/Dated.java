package com.exam2.Model;

import java.util.Date;

public interface Dated {
    Date getCreatedAt();

    Dated setCreatedAt(Date createdAt);

    Date getUpdatedAt();

    Dated setUpdatedAt(Date updatedAt);
}
