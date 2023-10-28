ALTER TABLE rover
    ADD CONSTRAINT rover_status_check
        CHECK (rover_status IN ('FREE', 'SENT', 'IN_OPERATION', 'DECOMMISSIONED'));
