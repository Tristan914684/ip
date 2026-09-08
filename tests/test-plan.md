# Test Plan: Duplicate Task Prevention

## Scope

Verify that newly added tasks cannot duplicate an existing task while preserving existing
duplicates loaded from storage.

## Functional tests

| Test case | Expected result |
| --- | --- |
| Add two to-do tasks whose descriptions differ only in capitalization | Second task is rejected with `already added`. |
| Add two to-do tasks whose descriptions differ only in whitespace | Second task is rejected with `already added`. |
| Add deadlines with the same description and due date | Second deadline is rejected. |
| Add events with the same description and start/end dates | Second event is rejected. |
| Add tasks of different types with the same description | Both tasks are accepted. |
| Add deadlines with different due dates | Both tasks are accepted. |
| Add events with different start or end dates | Both tasks are accepted. |
| Mark an existing task complete, then add an otherwise identical task | New task is rejected. |
| Load a file that already contains duplicates | All loaded tasks are preserved. |
| Reject a duplicate | Task count, indexes, and storage contents remain unchanged. |

## Regression tests

- Existing parser validation messages remain unchanged.
- Existing list, find, mark, unmark, delete, and date-filter behavior remains unchanged.
- Successful task additions retain the existing output and storage format.

## Automated tests

- `TaskListTest` covers task identity and duplicate preservation.
- `ClslTest` covers the exact user-facing duplicate response and unchanged task list.
