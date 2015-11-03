package network.thunder.core.communication.objects.p2p;

import com.google.gson.Gson;
import network.thunder.core.communication.objects.p2p.sync.ChannelStatusObject;
import network.thunder.core.communication.objects.p2p.sync.PubkeyChannelObject;
import network.thunder.core.communication.objects.p2p.sync.PubkeyIPObject;

/**
 * Object for sending across the different sync objects.
 * This is only used to span they way to the other node, where the node will unbox the object again.
 */
public class DataObject {

    public final static int TYPE_IP_PUBKEY = 1;
    public final static int TYPE_CHANNEL_PUBKEY = 2;
    public final static int TYPE_CHANNEL_STATUS = 3;

    public int type = 0;

    public String data = "";

    public DataObject (Object object) {
        if (object instanceof PubkeyIPObject) {
            type = TYPE_IP_PUBKEY;
            PubkeyIPObject p = (PubkeyIPObject) object;
            data = new Gson().toJson(p);
        } else if (object instanceof PubkeyChannelObject) {
            type = TYPE_CHANNEL_PUBKEY;
            PubkeyChannelObject p = (PubkeyChannelObject) object;
            data = new Gson().toJson(p);
        } else if (object instanceof ChannelStatusObject) {
            type = TYPE_CHANNEL_STATUS;
            ChannelStatusObject p = (ChannelStatusObject) object;
            data = new Gson().toJson(p);
        } else {
            throw new RuntimeException("Object not supported currently");
        }
    }

    public P2PDataObject getObject () {
        if (type == TYPE_IP_PUBKEY) {
            return new Gson().fromJson(data, PubkeyIPObject.class);
        }
        if (type == TYPE_CHANNEL_PUBKEY) {
            return new Gson().fromJson(data, PubkeyChannelObject.class);
        }
        if (type == TYPE_CHANNEL_STATUS) {
            return new Gson().fromJson(data, ChannelStatusObject.class);
        }
        throw new RuntimeException("Object not supported currently");
    }

    public PubkeyChannelObject getPubkeyChannelObject () {
        if (type == TYPE_CHANNEL_PUBKEY) {
            return new Gson().fromJson(data, PubkeyChannelObject.class);
        }
        throw new RuntimeException("Not correct object..");
    }

    public PubkeyIPObject getPubkeyIPObject () {
        if (type == TYPE_IP_PUBKEY) {
            return new Gson().fromJson(data, PubkeyIPObject.class);
        }
        throw new RuntimeException("Not correct object..");
    }

}