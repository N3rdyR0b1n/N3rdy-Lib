package n3rdyr0b1n.lib.items.nbt;

import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;
import net.minecraft.nbt.NbtTagSizeTracker;
import net.minecraft.nbt.NbtType;
import net.minecraft.nbt.scanner.NbtScanner;
import net.minecraft.nbt.visitor.NbtElementVisitor;

import java.io.*;

public class NbtObject implements NbtElement {
    public static final byte NBTTAG = 123;
    protected String string;
    public NbtObject(String name) {
        this.string = name;
    }

    public NbtObject clone(){
        try {
            return (NbtObject)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String toString() {
        return "NbtObject";
    }

    @Override
    public byte getType() {
        return NBTTAG;
    }

    @Override
    public NbtType<?> getNbtType() {
        return TYPE;
    }

    @Override
    public NbtElement copy() {
        return this;
    }

    @Override
    public int getSizeInBytes() {
        return this.Serialise().length + 24;
    }

    @Override
    public void accept(NbtElementVisitor var1) {
        var1.visitString(NbtString.of(string));
    }

    @Override
    public NbtScanner.Result doAccept(NbtScanner var1) {
        return NbtScanner.Result.BREAK;
    }


    static NbtObject readObject(DataInput input, NbtTagSizeTracker tracker) throws IOException {
        int a = input.readInt();
        tracker.add(a);
        byte[] data = new byte[a];
        input.readFully(data);
        try {
            ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(data));
            Object o = stream.readObject();
            stream.close();
            return (NbtObject) o;
        } catch (IOException | ClassNotFoundException e) {
            return new ErrorNbtObject();
        }
    }
    @Override
    public void write(DataOutput output) throws IOException {
        byte[] result = Serialise();
        if (result.length == 0) {
            result = ErrorNbtObject.Serialised;
        }
        int a = result.length;
        output.writeInt(a);
        output.write(result);
    }
    public byte[] Serialise() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
        }
        catch (IOException exception) {
            return new byte[0];
        }
        return outputStream.toByteArray();
    }

    public static final NbtType<NbtObject> TYPE = new NbtType.OfVariableSize<NbtObject>(){

        @Override
        public NbtObject read(DataInput dataInput, int i, NbtTagSizeTracker nbtTagSizeTracker) throws IOException {
            return NbtObject.readObject(dataInput, nbtTagSizeTracker);
        }

        @Override
        public NbtScanner.Result doAccept(DataInput input, NbtScanner visitor) throws IOException {
            byte[] a = new byte[input.readShort()];
            input.readFully(a);
            visitor.visitByteArray(a);
            return NbtScanner.Result.CONTINUE;
        }

        @Override
        public void skip(DataInput input) throws IOException {
            int a = input.readInt();
            input.skipBytes(a);
        }

        @Override
        public String getCrashReportName() {
            return "NBTOBJECT";
        }

        @Override
        public String getCommandFeedbackName() {
            return "NBTOBJECT";
        }
    };
    @Override
    public String asString() {
        return string;
    }
}
