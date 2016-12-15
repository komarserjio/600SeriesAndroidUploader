package info.nightscout.android.medtronic.message;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import info.nightscout.android.USB.UsbHidDriver;
import info.nightscout.android.medtronic.MedtronicCnlSession;

/**
 * Created by volker on 10.12.2016.
 */

public class CloseConnectionRequestMessage extends ContourNextLinkBinaryRequestMessage {
    public CloseConnectionRequestMessage(MedtronicCnlSession pumpSession, byte[] payload) throws ChecksumException {
        super(CommandType.CLOSE_CONNECTION, pumpSession, payload);
    }

    public OpenConnectionResponseMessage send(UsbHidDriver mDevice) throws IOException, TimeoutException, EncryptionException, ChecksumException {
        sendMessage(mDevice);

        OpenConnectionResponseMessage response = new OpenConnectionResponseMessage(mPumpSession, readMessage(mDevice));

        // FIXME - We need to care what the response message is - wrong MAC and all that
        return response;
    }
}