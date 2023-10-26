package org.openqa.selenium.bidi.script;

import static java.util.Collections.unmodifiableMap;
import static org.openqa.selenium.bidi.script.RemoteReference.RemoteReferenceType.HANDLE;
import static org.openqa.selenium.bidi.script.RemoteReference.RemoteReferenceType.SHARED_ID;

import java.util.Map;
import java.util.TreeMap;

public class RemoteReference extends LocalValue {
  public enum RemoteReferenceType {
    HANDLE("handle"),
    SHARED_ID("sharedId");

    private final String type;

    RemoteReferenceType(String type) {
      this.type = type;
    }

    @Override
    public String toString() {
      return type;
    }
  }

  private String handle;
  private String sharedId;

  public RemoteReference(String handle, String sharedId) {
    this.handle = handle;
    this.sharedId = sharedId;
  }

  public RemoteReference(RemoteReferenceType type, String value) {
    if (HANDLE.equals(type)) {
      this.handle = value;
    } else {
      this.sharedId = value;
    }
  }

  public Map<String, Object> asMap() {
    Map<String, String> toReturn = new TreeMap<>();
    if (handle != null) {
      toReturn.put(HANDLE.toString(), this.handle);
    }

    if (sharedId != null) {
      toReturn.put(SHARED_ID.toString(), this.sharedId);
    }

    return unmodifiableMap(toReturn);
  }
}
