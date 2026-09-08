# Clsl User Guide

// Update the title above to match the actual product name

// Product screenshot goes here

Clsl is a simple task manager for to-dos, deadlines, and events.

## Adding tasks

Add a to-do with:

```text
todo <description>
```

Add a deadline with:

```text
deadline <description> /by <yyyy-mm-dd>
```

Add an event with:

```text
event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>
```

## Duplicate tasks

Clsl rejects a newly added task when an existing task of the same type has the same description,
ignoring capitalization and whitespace. Deadlines must also have the same due date, and events
must also have the same start and end dates.

For example, after adding `todo Buy milk`, the following command is rejected:

```text
todo BU Y   MILK
```

The response is:

```text
already added
```

Completion status does not make an otherwise identical task different. Existing duplicate tasks
from older data files are preserved.

